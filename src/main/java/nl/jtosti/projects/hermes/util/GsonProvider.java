package nl.jtosti.projects.hermes.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonProvider {
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
            .create();

    public static Gson getGson() {
        return gson;
    }
}
