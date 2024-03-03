package com.himehime.lib;
public class Member extends Customer {
    protected String name;
    protected String phone;
    protected int point;
    protected boolean activated;
    // int id, boolean memberStatus, boolean vipStatus,
    
    // ctor
    public Member(){
        super();
        this.name = "";
        this.phone = "";
        this.point = 0;
        this.activated = true;
    }

    public Member(int id) {
        super(id);
        this.name = "";
        this.phone = "";
        this.point = 0;
        this.activated = true;
    }
    
    public Member(int id, String name, String phone) {
        super(id);
        this.name = name;
        this.phone = phone;
        this.point = 0;
        this.activated = true;
    }
    public Member(Customer customer, String name, String phone) {
        // customer.getId(), customer.isMember(), customer.isVip()
        super(customer);
        this.name = name;
        this.phone = phone;
        this.point = 0;
        this.activated = true;
    }
    public Member(VIPMember vip){
        // vip.getId(), vip.isMember(), vip.isVip()
        super(vip);
        this.name = vip.getName();
        this.phone = vip.getPhone();
        this.point = vip.getPoint();
        this.activated = vip.isActivated();
    }
    // cctor
    public Member(Member member) {
        super(member);
        this.name = member.getName();
        this.phone = member.getPhone();
        this.point = member.getPoint();
        this.activated = member.isActivated();
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public int getPoint() {
        return point;
    }
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setPoint(int point) {
        this.point = point;
    }
    
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void addPoint(int point) {
        this.point += point;
    }
    public void usePoint(int point) {
        this.point -= point;
    }
    public int getDiscount(int price, int point){
        return Math.min(price, point);
    }
    public boolean isActivated() {
        return this.activated;
    }
    public void activate() {
        this.activated = true;
    }
    public void deactivate() {
        this.activated = false;
    }
    @Override
    public void addTransaction(FixedBill transaction) {
        this.transactionList.add(transaction);
    }

    // Upgrade methods
    public VIPMember upgradeToVIP(String name, String phone) {
        VIPMember member = new VIPMember(this.id, name, phone);
        return member;
    }
}
