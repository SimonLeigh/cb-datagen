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
    Bucket bucket = BucketFactory.getBucket();
    JsonDocument doc = bucket.get(CountSamplesByType.COUNT_TYPE_V_KEY);

    /**
     * The output stream to use
     */
    private JspWriter out;

    @Override
    public void drawChart() throws IOException {

        if (doc != null) {

            out.println("function drawChart() {");
            out.println("var data = new google.visualization.DataTable();");
            out.println("data.addColumn('Type','Count');");

            String rows = "";
            Set<String> names = doc.content().getNames();

            for (String name : names) {

                String e = "['" + name + "'," + doc.content().getInt(name) + "]";

                if (rows.equals("")) {
                    rows += e;
                } else {
                    rows += ",'" + e;
                }
            }

            out.println("data.addRows([" + rows + "]);");
            out.println("var options = {'title':'Types','width':400,'height':300};");
            out.println("var chart = new google.visualization.PieChart(document.getElementById('type_chart'));");
            out.println("chart.draw(data, options);");
            out.println("}");

        }

    }
}
