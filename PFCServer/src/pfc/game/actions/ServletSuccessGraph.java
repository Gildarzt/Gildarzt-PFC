package pfc.game.actions;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import pfc.game.domain.Patient;
import pfc.game.domain.Psicologo;
import pfc.game.domain.Report;

import com.opensymphony.xwork2.ActionContext;

/**
 * Servlet implementation class ServletSuccessGraph
 */
@SuppressWarnings("serial")
public class ServletSuccessGraph extends HttpServlet {
	private XYDataset generaDatos() {
		try {
			Psicologo ad = (Psicologo) ActionContext.getContext().getSession().get("psicologo");
			if (ad == null)
				throw new Exception("No está usted logueado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Patient pat=(Patient)ActionContext.getContext().getSession().get("Patient");
		XYSeries serie1 = new XYSeries("Aciertos por informe");
		for(Report rep:pat.getReportList())
			serie1.add(rep.getId(), rep.getnSuccess());
	 
		XYSeriesCollection xyseriescollection =new XYSeriesCollection();
		xyseriescollection.addSeries(serie1);
	 
		return xyseriescollection;
	}
	 
	 private static JFreeChart generaGrafico(XYDataset xydataset) {
		 JFreeChart jfreechart = ChartFactory.createXYLineChart("Lineal", "X", "Y",xydataset, 
				 PlotOrientation.VERTICAL,true, true, false);
	 
		 XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		 xyplot.setBackgroundPaint(Color.white);
		 xyplot.setDomainGridlinePaint(Color.gray);
		 xyplot.setRangeGridlinePaint(Color.gray);
		 XYLineAndShapeRenderer xylineandshaperenderer=(XYLineAndShapeRenderer) xyplot.getRenderer();
		 xylineandshaperenderer.setBaseShapesVisible(true);
	 
		 return jfreechart;
	 }
	 
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
		 response.setContentType("image/jpeg");
		 OutputStream out = response.getOutputStream();
	 
		 XYDataset xydataset = generaDatos();
		 JFreeChart grafico = generaGrafico(xydataset);
		 ChartUtilities.writeChartAsJPEG(out, grafico, 400, 300);
	 
		 out.close();
	 }
	 
	 // <editor-fold defaultstate="collapsed" desc="métodos doGet y doPost creados por NetBeans">
	 /**
	 * Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	 @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
		 processRequest(request, response);
	 }
	 
	 /**
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	 @Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
		 processRequest(request, response);
	 }
	 
	 /**
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */
	 @Override
	 public String getServletInfo() {
		 return "Short description";
	 }// </editor-fold>
}
