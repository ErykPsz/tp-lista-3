package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.DoorState;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.LockStatus;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorObjectAdapter implements Door {
    public ThirdPartyDoor thirdPartyDoor = new ThirdPartyDoor();
    
    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            thirdPartyDoor.unlock(code);
            thirdPartyDoor.setState(DoorState.OPEN);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        } catch (CannotChangeStateOfLockedDoor e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            thirdPartyDoor.setState(DoorState.CLOSED);
        } catch (CannotChangeStateOfLockedDoor e) {
        }
    }

    @Override
    public boolean isOpen() {
        return thirdPartyDoor.getState() == DoorState.OPEN;
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation)
            throws IncorrectDoorCodeException, CodeMismatchException {

        if (!newCode.equals(newCodeConfirmation)) {
            throw new CodeMismatchException();
        }

        try {
            thirdPartyDoor.unlock(oldCode);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }

        try {
            thirdPartyDoor.setNewLockCode(newCode);
        } catch (CannotChangeCodeForUnlockedDoor e) {
            throw new RuntimeException(e);
        } finally {
            thirdPartyDoor.lock();
        }
    }

    @Override
    public boolean testCode(String code) {
        LockStatus originalStatus = thirdPartyDoor.getLockStatus();
        try {
            thirdPartyDoor.unlock(code);
        } catch (CannotUnlockDoorException e) {
            return false;
        }

        if (originalStatus == LockStatus.LOCKED) {
            thirdPartyDoor.lock();
        }

        return true;
    }
}
