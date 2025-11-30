package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.DoorTest;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;

public class ThirdPartyDoorAdapterTest extends DoorTest {

    @Override
    protected Door createDoorUnderTest() {
        return new ThirdPartyDoorAdapter();
    }

    @Override
    protected String getDefaultDoorCode() {
        return ThirdPartyDoor.DEFAULT_CODE;
    }
}
