package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {
	
	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
		quake.getLocation();
		// Add a radius property and then set the properties
/*		java.util.HashMap<String, Object> properties = quake.getProperties();
		float magnitude = Float.parseFloat(properties.get("magnitude").toString());
		properties.put("radius", 2*magnitude );
		setProperties(properties);
		this.radius = 1.75f*getMagnitude();*/
		
		
		// setting field in earthquake marker
		isOnLand = false;
	}
	

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// Drawing a centered square for Ocean earthquakes
		// DO NOT set the fill color.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered square.
		
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method
		pg.pushStyle();
		float r=this.radius/2;
		float lat = (float) this.getLocation().x;
		float lon = (float)this.getLocation().y;
		float x1=x-r;
		float y1 = y-r;
		float x2 = x+r;
		float y2 = y+r;
		//pg.triangle(x1,y1,x2,y2,x3,y3);
		//pg.rectMode(CENTER);
		//pg.triangle(x1, y1, x2, y2, x3, y3);
		pg.rect(x1,y1,r*2,r*2);
		
		
		// Restore previous drawing style
		pg.popStyle();
		
		
	}
	


	

}
