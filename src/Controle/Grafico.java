
package Controle;

import Apresentacao.frmPrincipal;
import Modelo.Voto;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;



public class Grafico extends JFrame   
{
    private static final long serialVersionUID = 1L;
    VotoUtil v = new VotoUtil();
    
  
    public Grafico() {
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               exitGraph();
            }
        });
       
        this.setTitle("Grafico Resultado votação");
        setContentPane(createDemoPanel( ));
    }
   
    public void exitGraph() 
    {
        this.dispose();
        frmPrincipal u  = new frmPrincipal(null, true);
        u.setVisible(true); 
    }
    
    public PieDataset createDataset( ) {
        
        ArrayList<Voto> retorna_numero_votos = v.retorna_numero_votos();
        DefaultPieDataset dataset = new DefaultPieDataset( );
        for (int i = 0; i < retorna_numero_votos.size(); ++i) 
        {
            Voto obj = (Voto) retorna_numero_votos.get(i);
            
            dataset.setValue(obj.getNome() +": "+ obj.getContagem(), Double.valueOf(Integer.parseInt(obj.getContagem())));  
        }
   
      return dataset;         
   }
    
    public JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Voto",   // chart title 
         dataset,          // data    
         true,             // include legend   
         false, 
         false);

      return chart;
   }

    
    public ChartPanel createDemoPanel( ) 
    {
                     
        
        JFreeChart chart = this.createChart(createDataset( ) );
      
        
        
        return new ChartPanel(chart); 
   }
    
    

    
   
}