/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.api.utils.command.datatypes;

import baritone.api.pathing.goals.GoalXZ;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.command.helpers.arguments.ArgConsumer;

import java.util.stream.Stream;

public class RelativeGoalXZ implements IDatatypePost<GoalXZ, BetterBlockPos> {
    final RelativeCoordinate[] coords;

    public RelativeGoalXZ() {
        coords = new RelativeCoordinate[0];
    }

    public RelativeGoalXZ(ArgConsumer consumer) {
        coords = new RelativeCoordinate[]{
                consumer.getDatatype(RelativeCoordinate.class),
                consumer.getDatatype(RelativeCoordinate.class)
        };
    }

    @Override
    public GoalXZ apply(BetterBlockPos origin) {
        return new GoalXZ(
                coords[0].applyFloor(origin.x),
                coords[1].applyFloor(origin.z)
        );
    }

    @Override
    public Stream<String> tabComplete(ArgConsumer consumer) {
        if (consumer.hasAtMost(2)) {
            return consumer.tabCompleteDatatype(RelativeCoordinate.class);
        }

        return Stream.empty();
    }
}
