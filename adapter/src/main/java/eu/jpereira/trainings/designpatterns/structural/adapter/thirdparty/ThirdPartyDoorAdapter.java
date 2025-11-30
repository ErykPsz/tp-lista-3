package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.DoorState;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.LockStatus;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdapter extends ThirdPartyDoor implements Door {

    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            unlock(code);
            setState(DoorState.OPEN);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        } catch (CannotChangeStateOfLockedDoor e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            setState(DoorState.CLOSED);
        } catch (CannotChangeStateOfLockedDoor e) {
        }
    }

    @Override
    public boolean isOpen() {
        return getState() == DoorState.OPEN;
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation)
            throws IncorrectDoorCodeException, CodeMismatchException {

        if (!newCode.equals(newCodeConfirmation)) {
            throw new CodeMismatchException();
        }

        try {
            unlock(oldCode);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }

        try {
            setNewLockCode(newCode);
        } catch (CannotChangeCodeForUnlockedDoor e) {
            throw new RuntimeException(e);
        } finally {
            lock();
        }
    }

    @Override
    public boolean testCode(String code) {
        LockStatus originalStatus = getLockStatus();
        try {
            unlock(code);
        } catch (CannotUnlockDoorException e) {
            return false;
        }

        if (originalStatus == LockStatus.LOCKED) {
            lock();
        }

        return true;
    }
}
