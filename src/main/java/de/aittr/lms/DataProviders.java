//package de.aittr.lms;
//
//import de.aittr.lms.dto.NewUserDto;
//import de.aittr.lms.models.NewUser;
//import org.testng.annotations.DataProvider;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class DataProviders {
//
//    @DataProvider
//    public Iterator<Object[]> addWrongUsersDataFromCsv() throws IOException {
//        List<Object[]> list = new ArrayList<>();
//
//        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/WrongUserData.csv"));
//
//        String line = reader.readLine();
//
//        while (line != null) {
//            String[] split = line.split(";");
////            list.add(new Object[]{
////                    new NewUser().setCohort(split[0])
////                            .setFirstName()
//
////            });
//        }
//
//        return list;
//    }
//}
