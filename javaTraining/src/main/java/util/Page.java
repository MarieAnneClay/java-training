package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import dto.ComputerDTO;
import dto.ComputerMapper;
import service.ServiceCompany;
import service.ServiceComputer;

public class Page {
    private static Map<String, String> initialMap = new HashMap<String, String>();
    static {
        initialMap.put("search", "");
        initialMap.put("numberOfComputerByPage", "10");
        initialMap.put("currentPage", "0");
        initialMap.put("sort", "cr.name");
        initialMap.put("order", "ASC");
        initialMap.put("orderName", "DESC");
        initialMap.put("orderIntroduced", "DESC");
        initialMap.put("orderDiscontinued", "DESC");
        initialMap.put("orderCompany", "DESC");
    }
    Map<String, String> map = new HashMap<String, String>(initialMap);
    private static ServiceComputer serviceComputer = ServiceComputer.getInstance();
    private static ServiceCompany serviceCompany = ServiceCompany.getInstance();

    public HttpServletRequest getRequest(HttpServletRequest request) {
        for (Entry<String, String> entry : map.entrySet()) {
            String parameterName = entry.getKey();
            String parameterValue = request.getParameter(parameterName);
            if (parameterValue != null) {
                map.put(parameterName, parameterValue);
            }
        }

        map.put(getSort(map.get("sort")), getOrder(map.get("sort"), map.get("order")));

        for (Entry<String, String> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }

        request.setAttribute("size", serviceComputer.getCount(map.get("search")));
        request.setAttribute("computers", getComputerDTO());
        request.setAttribute("serviceCompany", serviceCompany);

        return request;

    }

    public ArrayList<ComputerDTO> getComputerDTO() {
        return ComputerMapper.convertComputersToDTOS(
                serviceComputer.getComputerByName(map.get("search"), Integer.parseInt(map.get("numberOfComputerByPage")), Integer.parseInt(map.get("currentPage")), map.get("sort"), map.get("order")));
    }

    public String getOrder(String sort, String order) {
        switch (sort) {
        case "cr.name":
            return reverse(order);

        case "introduced":
            return reverse(order);

        case "discontinued":
            return reverse(order);

        case "cy.name":
            return reverse(order);
        default:
            return reverse(order);

        }
    }

    public String getSort(String sort) {
        switch (sort) {
        case "cr.name":
            return "orderName";

        case "introduced":
            return "orderIntroduced";

        case "discontinued":
            return "orderDiscontinued";

        case "cy.name":
            return "orderCompany";

        default:
            return "orderName";

        }
    }

    public String reverse(String order) {
        if (order.equals("DESC")) {
            order = "ASC";
        } else {
            order = "DESC";
        }
        return order;
    }

}
