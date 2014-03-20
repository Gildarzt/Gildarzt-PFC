package pfc.game.actions;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
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
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FailGraph extends ActionSupport{
	private JFreeChart chart;
	public String execute() throws Exception {
		try {
			Psicologo ad = (Psicologo) ActionContext.getContext().getSession().get("psicologo");
			if (ad == null)
				throw new Exception("No está usted logueado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XYDataset xydataset = generaDatos();
		chart = generaGrafico(xydataset);
		return SUCCESS;
	}
	private XYDataset generaDatos() {
		Patient pat=(Patient)ActionContext.getContext().getSession().get("Patient");
		XYSeries serie1 = new XYSeries("Fallos por informe");
		for(Report rep:pat.getReportList())
			serie1.add(rep.getId(), rep.getnFailure());
	 
		XYSeriesCollection xyseriescollection =new XYSeriesCollection();
		xyseriescollection.addSeries(serie1);
	 
		return xyseriescollection;
	}
	private JFreeChart generaGrafico(XYDataset xydataset) {
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
	public JFreeChart getChart() {
		return chart;
	}
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
}
