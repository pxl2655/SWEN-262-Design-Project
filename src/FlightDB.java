import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by calvinclark on 10/8/17.
 */
public class FlightDB {
    private List<Flight> flights;
    private List<Itinerary> itineraries;
    public FlightDB(List<Flight> flights, List<Itinerary> itineraries){
        this.flights = flights;
        this.itineraries = itineraries;
    }

    public List<Itinerary> checkID(int id){
        List<Itinerary> foundItineraries = new ArrayList<>();
        for (Itinerary i : itineraries){
            if (i.getID() == id){
                foundItineraries.add(i);
            }
        }
        return foundItineraries;
    }

    public List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder){
        List<Itinerary> foundItineraries = new ArrayList<>();
        Comparator<FlightInterface> comparator;
        if (numConnections <= 2) {
            for (Itinerary i : itineraries){
                if (i.getOrigin().equals(origin) && i.getDestination().equals(destination)
                        && i.getConnections() <= numConnections){
                    foundItineraries.add(i);
                }
            }
        }
        if(sortOrder.equals("arrival")){
            comparator = new ArrivalTimeComparator();
        }
        else if (sortOrder.equals("airfare")){
            comparator = new AirfareComparator();
        }
        else {
            comparator = new DepartureTimeComparator();
        }
        foundItineraries.sort(comparator);
        return foundItineraries;
    }

}
