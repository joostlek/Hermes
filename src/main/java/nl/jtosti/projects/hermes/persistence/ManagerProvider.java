package nl.jtosti.projects.hermes.persistence;

public class ManagerProvider {
    private static UserManager userManager = new UserManager();
    private static ImageManager imageManager = new ImageManager();
    private static LocationManager locationManager = new LocationManager();
    private static PromotionManager promotionManager = new PromotionManager();
    private static ScreenManager screenManager = new ScreenManager();
    private static TypeManager typeManager = new TypeManager();

    public static UserManager getUserManager() {
        return userManager;
    }

    public static ImageManager getImageManager() {
        return imageManager;
    }

    public static LocationManager getLocationManager() {
        return locationManager;
    }

    public static PromotionManager getPromotionManager() {
        return promotionManager;
    }

    public static ScreenManager getScreenManager() {
        return screenManager;
    }

    public static TypeManager getTypeManager() {
        return typeManager;
    }
}
