package com.couchbase.demo.iot.vehicles.viz;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.demo.iot.vehicles.conn.BucketFactory;
import com.couchbase.demo.iot.vehicles.spark.CountSamplesByType;
import java.io.IOException;
import java.util.Set;
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author david
 */
public class TypeChart implements Chart {

    /**
     * Chart input
     */
    private Bucket bucket = BucketFactory.getBucket();
    private JsonDocument doc = bucket.get(CountSamplesByType.COUNT_TYPE_V_KEY);

    /**
     * The output stream to use
     */
    private JspWriter out;
    
    
    private String id;

    
    
    public TypeChart(JspWriter out, String id) {
        this.out = out;
        this.id = id;
    }

    
    @Override
    public void setOut(JspWriter out) {
        this.out = out;
    }

    @Override
    public JspWriter getOut() {
        return out;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
    
    
    
    @Override
    public void drawChart() throws IOException {

        if (doc != null) {

            out.println("function drawChart_" + id + "() {");
            out.println("var data = new google.visualization.DataTable();");
            out.println("data.addColumn('string','Type');");
            out.println("data.addColumn('number','Count');");

            String rows = "";
            Set<String> names = doc.content().getNames();

            for (String name : names) {

                String e = "['" + name + "'," + doc.content().getInt(name) + "]";

                if (rows.equals("")) {
                    rows += e;
                } else {
                    rows += "," + e;
                }
            }

            out.println("data.addRows([" + rows + "]);");
            out.println("var options = {'title':'Types','width':400,'height':300};");
            out.println("var chart = new google.visualization.PieChart(document.getElementById('"+ id  + "'));");
            out.println("chart.draw(data, options);");
            out.println("}");

        }

    }
}
