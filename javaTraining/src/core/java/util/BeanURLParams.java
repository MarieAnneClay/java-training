package core.java.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

public class BeanURLParams {
    private HashMap<String, String> paramNameVSValue;

    public BeanURLParams() {
        paramNameVSValue = new HashMap<>();
    }

    public BeanURLParams(HttpServletRequest request) {
        paramNameVSValue = new HashMap<>();
        this.getParameterFromRequest(request);
    }

    public String buildUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("?");

        Iterator<String> it = paramNameVSValue.keySet().iterator();
        while (it.hasNext()) {
            String paramName = it.next();
            sb.append(paramName).append("=").append(paramNameVSValue.get(paramName));
            if (it.hasNext()) {
                sb.append("&");
            }
        }

        return sb.toString();
    }

    public void getParameterFromRequest(HttpServletRequest request) {
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paramName = enu.nextElement();
            paramNameVSValue.put(paramName, request.getParameter(paramName));
        }
    }

    public void overrideParam(String key, String val) {
        paramNameVSValue.put(key, val);
    }

    // public void overrideParam(String key, Integer val) {
    // paramNameVSValue.put(key, String.valueOf(val.intValue()));
    // }

}
