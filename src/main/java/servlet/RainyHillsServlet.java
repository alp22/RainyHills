package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.WaterShedMethod1;
import util.RainyHillsException;
import util.SimpleValidationAndConversion;

/**
 * Servlet implementation class RainyHillsServlet
 */
public class RainyHillsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/// For detailed method output, see servlet init parameters in web.xml
		String isDetailed = getServletConfig().getInitParameter("detailed");

		// Just to give a content type
		// Its nice to have a json output, if this is some kind of a micromodule 
		// or a middleware module, so integration will be easier
		response.setContentType("application/json");
		if (!request.getParameterMap().containsKey("terrain"))
		{

			response.getWriter()
					.write("{\"error\":\"Bad Useage. Use parameter terrain with integer values seperated by commas\"}");
			return;
		}

		String terrainStr = request.getParameter("terrain");

		SimpleValidationAndConversion simpleValidationAndConversion = new SimpleValidationAndConversion();

		try
		{
			Integer[] terrain = simpleValidationAndConversion.validateAndConvert(terrainStr);
			
			WaterShedMethod1 waterShedMethod1 = new WaterShedMethod1();

			if (isDetailed.equals("true"))
			{
				waterShedMethod1.setDetailed(true);
			}
			int water = waterShedMethod1.getWater(terrain);
			response.getWriter().write("{\"result\":" + water + "}");
			return;

		}
		catch (RainyHillsException e)
		{
			response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}

}
