package retrofit.aishwarya.com.customview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by aishwarya on 20/7/16.
 */
public class ViewUtils {

    /**
     * Generate a value suitable for use in {@link #View.setId(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     * View.generateViewId() method is there from API 17 onwards. This method is for supporting below 17 APIs also
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        return ViewId.generateViewId();
    }


    private static class ViewId {
        private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

        /**
         * Generate a value suitable for use in {@link #setId(int)}.
         * This value will not collide with ID values generated at build time by aapt for R.id.
         *
         * @return a generated ID value
         */
        private static int generateViewId() {
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }

            }
        }
    }
}
