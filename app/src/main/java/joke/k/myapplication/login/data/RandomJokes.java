package joke.k.myapplication.login.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RandomJokes {
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    @PrimaryKey()
   private int id;
   private String type;
   private String setup;
   private String punchline;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }

    public String getPunchline() {
        return punchline;
    }


}
