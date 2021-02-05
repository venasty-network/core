package net.verany.api.locationmanager;

import net.verany.api.loader.LoadObject;
import net.verany.api.module.VeranyProject;
import org.bukkit.Location;

public class LocationManager extends AbstractLocationManager {

    public LocationManager(VeranyProject project, String collection, String database) {
        super(project, collection, database);
        load(new LoadInfo<>("locations", VeranyLocations.class, new VeranyLocations()));
    }

    @Override
    public void save() {
        save("locations");
    }

    @Override
    public void createLocation(String name, Location location) {
        getData(VeranyLocations.class).getLocations().put(name, VeranyLocation.toVeranyLocation(location));
    }

    @Override
    public Location getLocation(String name) {
        return getData(VeranyLocations.class).getLocations().get(name).toLocation();
    }

    @Override
    public boolean existLocation(String name) {
        return getData(VeranyLocations.class).getLocations().containsKey(name);
    }
}
