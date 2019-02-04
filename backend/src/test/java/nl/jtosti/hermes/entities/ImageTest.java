//package nl.jtosti.hermes.entities;
//
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//
//class ImageTest {
//    private Image image;
//
//    private Screen screen = new Screen();
//
//    private Company user = new User();
//
//    @Test
//    @DisplayName("Test no args constructor")
//    void testNoArgsConstructor() {
//        image = new Image();
//
//        assertThat(image.getId()).isNull();
//        assertThat(image.getName()).isNull();
//        assertThat(image.getUploader()).isNull();
//        assertThat(image.getScreen()).isNull();
//        assertThat(image.getCreated()).isNull();
//        assertThat(image.getUpdated()).isNull();
//        assertThat(image.getUrl()).isNull();
//    }
//
//    @Test
//    @DisplayName("Test setters and getters")
//    void testSettersAndGetters() {
//        image = new Image("Image 1", "url", screen, user);
//        String name = "Image 2";
//        String url = "urls";
//        Screen screen1 = new Screen();
//        Company user1 = new User();
//
//        image.setName(name);
//        image.setUploader(user1);
//        image.setScreen(screen1);
//        image.setUrl(url);
//
//        assertThat(image.getName()).isEqualTo(name);
//        assertThat(image.getUrl()).isEqualTo(url);
//        assertThat(image.getUploader()).isEqualTo(user1);
//        assertThat(image.getScreen()).isEqualTo(screen1);
//    }
//
//    @Test
//    @DisplayName("Test equals")
//    void testEquals() {
//        image = new Image("Image 1", "url", screen, user);
//        image.setId(1L);
//
//        Image image1 = new Image("Image 1", "url", screen, user);
//        image1.setId(1L);
//
//        assertThat(image.equals(image1)).isTrue();
//
//        assertThat(image.equals((Object) screen)).isFalse();
//
//        image.setUrl("urls");
//        assertThat(image.equals(image1)).isFalse();
//        image.setId(2L);
//        assertThat(image.equals(image1)).isFalse();
//        image.setName("Image 2");
//        assertThat(image.equals(image1)).isFalse();
//    }
//
//    @Test
//    @DisplayName("Test hashCode")
//    void testHashCode() {
//        image = new Image();
//        assertThat(image.hashCode()).isEqualTo(image.hashCode());
//        Image image1 = new Image();
//        assertThat(image.hashCode()).isNotEqualTo(image1.hashCode());
//    }
//
//    @Test
//    @DisplayName("Test toString")
//    void testToString() {
//        image = new Image("Image 1", "url", screen, user);
//        assertThat(image.toString()).isEqualTo(String.format("<Image: %s>", image.getName()));
//        image.setId(1L);
//        assertThat(image.toString()).isEqualTo(String.format("<Image %s: %s>", image.getId(), image.getName()));
//    }
//}
