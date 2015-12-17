package org.jboss.as.quickstarts.utils;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

public class LoggingInterceptor {
    private Logger logger = Logger.getLogger("org.jboss.as.quickstarts");

    @AroundInvoke
    public Object logMethod(InvocationContext invocationContext) throws Exception {

        System.out.println(invocationContext.getTarget().toString()+"\n"+ invocationContext.getMethod().getName());

        logger.entering(invocationContext.getTarget().toString(),
                invocationContext.getMethod().getName());
        try {
            return invocationContext.proceed();
        } finally {
            logger.exiting(invocationContext.getTarget().toString(),
                    invocationContext.getMethod().getName());
        }
    }
}