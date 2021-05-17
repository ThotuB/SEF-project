package Tests;

import Databases.ProviderDTB;

public class ProviderDTB_Test {

    public static void main(String[] args) {
        ProviderDTB providerDTB = new ProviderDTB("src/main/resources/Databases/ProvidersDTB.json", "Thotu69");

        System.out.println(providerDTB);
    }
}
