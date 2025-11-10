package practice.arrays.singleloop;

/**
 * Input: height[] = [7, 4, 8, 2, 9]
 * Output: 3
 * Explanation: As 7 is the first element, it can see the sunset.
 * Similarly, 8 and 9 can see the sunset. 4 and 2 can't see the sunset because 7 and 8 are hiding it.
 *
 * Input: height[] = [2, 3, 4, 5]
 * Output: 4
 * Every tower can see the sunset as nothing is hiding it
 *
 */
public class CountBuildingsFacingSun {
    public static void main(String args[]) {
        int height1[] = {7, 4, 8, 2, 9};
        int height2[] = {2,3,4,5};

        System.out.println(buildingFacingSun(height1));
        System.out.println(buildingFacingSun(height2));
    }

    /**
     * anything greater than first element can see the building
     * @param height
     * @return
     */
    public static int buildingFacingSun(int height[]) {
        int count = 1;
        int firstBuildingHeight = height[0];
        for(int i=1; i<height.length; i++) {
            if(height[i] > firstBuildingHeight) {
                count++;
            }
        }
        return count;
    }
}
