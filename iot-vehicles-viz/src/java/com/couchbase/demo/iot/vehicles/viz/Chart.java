
package com.couchbase.demo.iot.vehicles.viz;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author david
 */
public interface Chart {
    
    public void setOut(JspWriter out);
    public JspWriter getOut();
    public String getId();
    public void setId(String id);
    
    public void drawChart() throws IOException;
}
