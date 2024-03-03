package com.himehime.lib;
public class VIPMember extends Member {
    private double discountRate;

    // ctor
    public VIPMember(){
        super();
        this.discountRate = 0.1; // default discount rate
    }

    public VIPMember(int id) {
        super(id);
        this.discountRate = 0.1; 
    }
    
    public VIPMember(int id, String name, String phone) {
        super(id, name, phone);
        this.discountRate = 0.1; // default discount rate
    }
    
    public VIPMember(Member member) {
        // member.getId(), member.isMember(), member.isVip(), member.getName(), member.getPhone()
        super(member.getId(),member.getName(), member.getPhone());
        this.discountRate = 0.1; // default discount rate
        this.setPoint(member.getPoint());
        this.setActivated(member.isActivated());
    }
    // cctor
    public VIPMember(VIPMember vip) {
        super(vip.getId(),vip.getName(), vip.getPhone());
        this.discountRate = vip.getDiscountRate();
        this.setPoint(vip.getPoint());
        this.setActivated(vip.isActivated());
    }

    public double getDiscountRate() {
        return this.discountRate;
    }
    
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
    
    @Override
    public int getDiscount(int price, int point) {
        int discountedPrice = (int) Math.round(price * this.discountRate);
        return Math.min(price, discountedPrice + point);
    }
    
    public Member downgradeToMember() {
        Member member = new Member(this);
        return member;
    }
}
