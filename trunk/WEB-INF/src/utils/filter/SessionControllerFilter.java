package utils.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import utils.hibernate.HibernateUtil;

/**
 * This class is a servlet filter which creates begins an Hibernate session before the loading, and closes the session at the end.
 * 
 * @author Automatika - JustInMind SL
 *
 */
public class SessionControllerFilter implements Filter {

	/** Logger */
	private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(SessionControllerFilter.class);

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		log.debug("SessionControllerFilter destroyed");
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HibernateUtil.getSession();
		try {
			chain.doFilter(request, response);

			//			Map cacheEntries = HibernateUtil.getSession().getSessionFactory().getStatistics().getSecondLevelCacheStatistics("").getEntries();
			//
			//			System.out.println(cacheEntries);

		}
		finally {
			HibernateUtil.closeSession();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		log.debug("SessionControllerFilter started");
	}

}
