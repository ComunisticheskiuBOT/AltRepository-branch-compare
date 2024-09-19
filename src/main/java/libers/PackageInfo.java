package libers;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageInfo {
    private String name;
    private int epoch;

    private String version;
    private String release;
    private String arch;
    private String disttag;
    private long buildtime;
    private String source;

    public PackageInfo(String name, int epoch, String version, String release, String arch, String disttag, long buildtime, String source) {
        this.name = name;
        this.epoch = epoch;
        this.version = version;
        this.release = release;
        this.arch = arch;
        this.disttag = disttag;
        this.buildtime = buildtime;
        this.source = source;
    }
    public int getEpoch() {
        return epoch;
    }

    public void setEpoch(int epoch) {
        this.epoch = epoch;
    }

    public String getDisttag() {
        return disttag;
    }

    public void setDisttag(String disttag) {
        this.disttag = disttag;
    }

    public Long getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(long buildtime) {
        this.buildtime = buildtime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    @Override
    public String toString(){
        return name + "-" + version + "-" + release + " (" + arch + ")";
    }
}