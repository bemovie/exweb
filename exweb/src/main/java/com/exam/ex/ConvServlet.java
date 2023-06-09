package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8000/exweb/dollar?num=10000&unit=won
//http://localhost:8000/exweb/dollar?num=10000&unit=dol

@WebServlet("/dollar")
public class ConvServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String numval = req.getParameter("num");
		String unitval = req.getParameter("unit");
		
		double fromMoney = Double.parseDouble(numval); //실수문자열을 실수로 변환
//		String wonDolUnit = unitval;
		double toMoney = 0;
		String fromUnit = "";
		String toUnit = "";
		
		switch (unitval) { 
		case "won" :
			toMoney = fromMoney / 1287; //원->달러
			fromUnit = "원";
			toUnit = "달러";
			break;
		case "dol" :
			toMoney = fromMoney * 1287; //달러->원
			fromUnit = "달러";
			toUnit = "원";
			break;
		default:
			break;			
		}	
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>          ");
		out.println("<html>                   ");
		out.println("<head>                   ");
		out.println("<meta charset=\"UTF-8\"> ");
		out.println("<title>HELLO</title>     ");
		out.println("</head>                  ");
		out.println("<body>                   ");
		out.println("<h1>" + fromMoney + fromUnit + " = " + toMoney + toUnit + "</h1>");
		out.println("</body>                  ");
		out.println("</html>                  ");

	}
	
}
