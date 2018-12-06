package module5;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** Implements a visual marker for cities on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
// TODO: Change SimplePointMarker to CommonMarker as the very first thing you do 
// in module 5 (i.e. CityMarker extends CommonMarker).  It will cause an error.
// That's what's expected.
public class CityMarker extends CommonMarker {
	
	public static int TRI_SIZE = 5;  // The size of the triangle marker
	
	public CityMarker(Location location) {
		super(location);
	}
	
	
	public CityMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	}

	
	/**
	 * Implementation of method to draw marker on the map.
	 */
	public void draw(PGraphics pg, float x, float y) {
		//drawMarker(pg,x,y);
		if (!hidden) {
			drawMarker(pg, x, y);
			if (selected) {
				showTitle(pg, x, y);  // You will implement this in the subclasses
			}
		}
	}
	
	/** Show the title of the city if this marker is selected */
	public void showTitle(PGraphics pg, float x, float y)
	{	
		int yellow = pg.color(255, 255, 1);
		int black = pg.color(0, 0, 0);
		int fontSize = 12;
		String text = String.format("City: %s, Country: %s, Population: %.2f Million",
				getCity(), getCountry(), getPopulation());
		float textWidth = pg.textWidth(text);
		pg.fill(yellow);
		pg.rect(x + TRI_SIZE, (y + TRI_SIZE) - fontSize, textWidth + 2, 14);
		pg.fill(black);
		pg.stroke(black);
		pg.textSize(fontSize);
		pg.text(text, x + TRI_SIZE, y + TRI_SIZE);


	}
public void showDanger(List<Marker> quakeMarkers, List<Marker> cityMarkers) {
	for (Marker cityMarker: cityMarkers){
		if (!this.equals(cityMarker)){
			cityMarker.setHidden(true);
		}else {
			//System.out.println("city : " + cityMarker.getStringProperty("name"));
			//System.out.println("this is : " + this.getStringProperty("name"));
			cityMarker.setHidden(false);
		}
	}
	
	// Hide the earthquakes which don't effect this cityMarker

	for (Marker earthquakeMarker: quakeMarkers){
		double threat = ((EarthquakeMarker) earthquakeMarker).threatCircle();
		if (earthquakeMarker.getDistanceTo(this.getLocation()) > threat*2){
			earthquakeMarker.setHidden(true);
		}
	}
}
	
	
	
	/* Local getters for some city properties.  
	 */
	public String getCity()
	{
		return getStringProperty("name");
	}
	
	public String getCountry()
	{
		return getStringProperty("country");
	}
	
	public float getPopulation()
	{
		return Float.parseFloat(getStringProperty("population"));
	}


	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		// TODO Auto-generated method stub

			pg.pushStyle();
			
			// triangle for each city
			pg.fill(150, 30, 30);
			pg.triangle(x, y-TRI_SIZE, x-TRI_SIZE, y+TRI_SIZE, x+TRI_SIZE, y+TRI_SIZE);
			
			pg.popStyle();
				
	}
	

}
