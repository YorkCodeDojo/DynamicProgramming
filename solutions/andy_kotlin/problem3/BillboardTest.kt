package problem3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


@DisplayName("Billboard Placement Unit Test")
internal class BillboardTest {

    @DisplayName("Given a set of Billboards")
    @ParameterizedTest(name = "{index} => When distance={0}, separation={1} & billboards={2} then expect income of {3} & placements of {4}")
    @MethodSource("billboardTestCaseProvider")
    fun billboardPlacementTest(distance: Int, separation: Int,  billboards: Map<Int,Int>,
                               expectedIncome: Int, expectedPlacements: List<Int>) {
        val (income, placements) = placeBillboards(distance, separation, billboards)
        Assertions.assertEquals(expectedIncome, income)
        Assertions.assertEquals(expectedPlacements, placements)
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun billboardTestCaseProvider(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(20, 5, mapOf(6 to 5, 7 to 6, 12 to 5, 13 to 3, 14 to 1), 10, listOf(6,12)),
                    Arguments.of(20, 4, mapOf(6 to 5, 7 to 6, 12 to 5, 13 to 3, 14 to 1), 11, listOf(7,12)),
                    Arguments.of(30, 4,
                            mapOf(6 to 4, 7 to 11, 10 to 8, 12 to 5, 13 to 9, 14 to 5, 17 to 9, 19 to 5, 22 to 4,
                                    23 to 5, 26 to 3, 28 to 3, 29 to 4, 30 to 7),
                            37, listOf(7,12,17,23,30)),
                    Arguments.of(30, 5,
                            mapOf(1 to 3, 3 to 6 , 6 to 4, 7 to 10, 10 to 8, 12 to 5, 13 to 3, 14 to 5, 17 to 9, 19 to 5,
                                    22 to 4, 23 to 5, 26 to 3, 28 to 3, 29 to 4, 30 to 7),
                            35, listOf(3,10,17,23,30))
            )
        }
    }

}