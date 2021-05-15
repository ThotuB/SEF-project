package Components;

import Databases.ProviderDTB;

public class ProviderCollection {

    private ProviderDTB providerDTB = new ProviderDTB();
    private Provider currentProvider;

    public ProviderCollection(String username) {

        providerDTB.readProviders();
        currentProvider = getProvider(username);

    }

    public ProviderDTB getProviderDTB() {
        return providerDTB;
    }

    public Provider getCurrentProvider() {
        return currentProvider;
    }

    public void updateCurrentProvider(Provider add) {
        currentProvider = add;
    }

    public void updateProviderDTB(ProviderDTB add) {
        providerDTB = add;
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