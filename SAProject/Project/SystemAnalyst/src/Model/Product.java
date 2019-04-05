package Model;

public class Product {
    private static int id,quantity,supplierid;
    private static String name,tubesize,weight,bodydimension,setpointtemp,display,usewith,cpu,memory,connected,power,flowrate,garanteeyear;
    private static double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTubesize() {
        return tubesize;
    }

    public void setTubesize(String tubesize) {
        this.tubesize = tubesize;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBodydimension() {
        return bodydimension;
    }

    public void setBodydimension(String bodydimension) {
        this.bodydimension = bodydimension;
    }

    public String getSetpointtemp() {
        return setpointtemp;
    }

    public void setSetpointtemp(String setpointtemp) {
        this.setpointtemp = setpointtemp;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getUsewith() {
        return usewith;
    }

    public void setUsewith(String usewith) {
        this.usewith = usewith;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getConnected() {
        return connected;
    }

    public void setConnected(String connected) {
        this.connected = connected;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getFlowrate() {
        return flowrate;
    }

    public void setFlowrate(String flowrate) {
        this.flowrate = flowrate;
    }

    public String getGaranteeyear() {
        return garanteeyear;
    }

    public void setGaranteeyear(String garanteeyear) {
        this.garanteeyear = garanteeyear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
