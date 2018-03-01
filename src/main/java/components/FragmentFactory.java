package components;

import fragments.AbstractFragment;

/**
 * Created by olena.kolesnyk on 13/02/2018.
 */
public class FragmentFactory {

    public <T extends AbstractFragment> T createFragment(Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("Cannot create an instance of " + type.getSimpleName(),
                    e.getCause());
        }
    }
}
