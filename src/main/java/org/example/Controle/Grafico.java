
package org.example.Controle;

import org.example.Apresentacao.FrmPrincipal;
import org.example.Modelo.Voto;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.util.ArrayList;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


public class Grafico extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    VotoUtil v = new VotoUtil();


    public Grafico() {

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitGraph();
            }
        });

        this.setTitle("Grafico Resultado votação");
        setContentPane(createDemoPanel());
    }

    public void exitGraph() {
        this.dispose();
        FrmPrincipal u = new FrmPrincipal(null, true);
        u.setVisible(true);
    }

    public PieDataset createDataset() {

        ArrayList<Voto> retorna_numero_votos = v.retorna_numero_votos();
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Voto retornaNumeroVoto : retorna_numero_votos) {
            dataset.setValue(retornaNumeroVoto.getNome() + ": " + retornaNumeroVoto.getContagem(), Double.valueOf(Integer.parseInt(retornaNumeroVoto.getContagem())));
        }

        return dataset;
    }

    public JFreeChart createChart(PieDataset dataset) {
        return ChartFactory.createPieChart(
                "Voto",   // chart title
                dataset,          // data
                true,             // include legend
                false,
                false);
    }


    public ChartPanel createDemoPanel() {
        JFreeChart chart = this.createChart(createDataset());
        return new ChartPanel(chart);
    }
}