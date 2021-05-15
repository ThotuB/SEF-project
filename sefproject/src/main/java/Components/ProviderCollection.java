package Components;

import Databases.ProviderDTB;

public class ProviderCollection {

    private ProviderDTB providerDTB = new ProviderDTB();
    private Provider currentProvider;

    public ProviderCollection(String username) {

        providerDTB.readProviders();
        currentProvider = getProvider(username);

    }

    public Provider getCurrentProvider() {
        return currentProvider;
    }

    public Provider getProvider(String username){

        for (Provider i: providerDTB.getData()) {
            if (i.getName().equals(username)) {
                return i;
            }
        }
        return null;
    }

}