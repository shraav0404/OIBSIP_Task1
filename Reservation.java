class Reservation {
    private String name;
    private String prn;
    private String train;
    private String type;
    private String date;
    private String splace;
    private String dplace;

    public Reservation(String name, String prn, String train, String type, String date, String splace, String dplace) {
        this.name = name;
        this.prn = prn;
        this.train = train;
        this.type = type;
        this.date = date;
        this.splace = splace;
        this.dplace = dplace;
    }

    public String getName() {
        return name;
    }

    public String getPrn() {
        return prn;
    }

    public String getTrain() {
        return train;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getSplace() {
        return splace;
    }

    public String getDplace() {
        return dplace;
    }
}

