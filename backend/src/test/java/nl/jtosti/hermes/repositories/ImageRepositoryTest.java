//package nl.jtosti.hermes.repositories;
//
//import nl.jtosti.hermes.image.ImageRepository;
//import nl.jtosti.hermes.image.StorageServiceInterface;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@DisplayName("Image Repository")
//@Tag("Repositories")
//public class ImageRepositoryTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private ImageRepository imageRepository;
//
//    @MockBean
//    private StorageServiceInterface storageService;
//
//    @Test
//    public void test() {
//
//    }
//}