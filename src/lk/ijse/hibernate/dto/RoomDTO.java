package lk.ijse.hibernate.dto;

public class RoomDTO {
    private String roomTypeId;
    private String roomType;
    private String keyMoney;
    private int qty;

    public RoomDTO() {
    }

    public RoomDTO(String roomTypeId, String roomType, String keyMoney, int qty) {
        this.roomTypeId = roomTypeId;
        this.roomType = roomType;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomTypeId='" + roomTypeId + '\'' +
                ", roomType='" + roomType + '\'' +
                ", keyMoney='" + keyMoney + '\'' +
                ", qty=" + qty +
                '}';
    }
}
