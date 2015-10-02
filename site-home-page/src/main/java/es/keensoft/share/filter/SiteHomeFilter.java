package es.keensoft.share.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Unordered filter: chain until first user logged request arrives
@WebFilter(urlPatterns={"/page/*"})
public class SiteHomeFilter implements Filter {
	
	private static final String SESSION_ATTRIBUTE_KEY_ON_SITE = "_alf_ON_SITE_NAVIGATION";
	private static final String REGEX_SITE_NAME = "\\/site\\/(.*)\\/(.*)";
	
	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		// Site home page redirection
		if (request.getPathInfo().indexOf("/site/") != -1 && request.getPathInfo().endsWith("/dashboard")) {
			if (doRedirect(request, request.getPathInfo())) {
		        response.sendRedirect(request.getContextPath() + "/page" + request.getPathInfo().replace("/dashboard", "/documentlibrary"));
			}
		}
		// If out of site scope, clear session
		if (request.getPathInfo().indexOf("/site/") == -1) {
			clearAttribute(request);
		}
        
    	chain.doFilter(req, resp);
	}
	
	private boolean doRedirect(HttpServletRequest request, String pathInfo) {
		
        HttpSession session = request.getSession();
        
        String site = getSiteName(pathInfo);
        
        if (session.getAttribute(SESSION_ATTRIBUTE_KEY_ON_SITE) != null && 
        	session.getAttribute(SESSION_ATTRIBUTE_KEY_ON_SITE).equals(site)) {
        	return false;
        } else {
        	session.setAttribute(SESSION_ATTRIBUTE_KEY_ON_SITE, site);
        	return true;
        }
        
	}
	
	private void clearAttribute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SESSION_ATTRIBUTE_KEY_ON_SITE);
	}
	
    public static String getSiteName(String shareUrl) {
        Pattern p1 = Pattern.compile(REGEX_SITE_NAME);
        Matcher m1 = p1.matcher(shareUrl);
        if (m1.find()) {
            return m1.group(1);
        } else {
            return null;
        }
    }
	
	@Override
	public void destroy() {}
}
