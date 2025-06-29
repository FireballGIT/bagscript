package bagstudios.bagscript.mods.Sdf;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BagErr extends Exception {
    public BagErr(String message) {
        super(message);
    }
}

public class RFC extends BagErr {
    public RFC(String message) {
        super(message);
    }
}

public class NDV extends BagErr {
    public NDV(String message) {
        super(message);
    }
}

public class VIA extends BagErr {
    public VIA(String message) {
        super(message);
    }
}

public class Sdf {
    // Class-level globals map to hold variables by name
    private HashMap<String, Object> globals = new HashMap<>();

    // Clears console if addon is empty, else clears variable in globals map
    public static void clear(String addon) {
        if (addon.equals("")) {
            try {
                // Clears Windows console
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e) {
                throw new RFC("ERROR! Failed to clear console. Err type RuntimeError, Err ID: r-fc");
            }
        } else {
            if (globals.containsKey(addon) {
                try {
                    globals.set(null)
                }

                catch (Exception e){
                    throw new NDV("ERROR! Unknown variable '" + addon + "'. Err type NameError, Err ID: n-dv");
                }
            }
        }
    }

    public static void pause(double seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (Exception e) {
            throw new BagErr("ERROR! Failed to halt program. Err type GenericBagError, Err ID: g-be/hp")
        }
    }
    
    public static void println(String str) {
        System.out.println(str);
    }

    public void frame(String action, String name, int currentFrame, int movingTo, int eql) {
        if (action.equals("import") && !name.equals("")) {
            try {
                byte[] fileBytes = getFile(fixExtension(name, ".png"));
                // TODO: process fileBytes (e.g., load image data, create frame, etc)
                System.out.println("Imported file: " + name);
            } catch (IOException e) {
                throw new BagErr("ERROR! Failed to import file. Err type GenericBagError, Err ID: g-be/if")
            }
        } else if (action.equals("move") && !name.equals("")) {
            try {
                // Example: just print out movingTo (can't modify int params here)
                System.out.println("Moving to frame: " + movingTo);
                // To actually move or update state, you'd want to store/mutate class fields or return values
            } catch (Exception e) {
                throw new VIA("ERROR! " + movingTo + " is not a valid integer. Err type ValueError, Err ID: v-ia")
            }
        } else {
            System.out.println("Unknown action or empty name.");
        }
    }

    // Helper to ensure filename has correct extension
    private String fixExtension(String filename, String ext) {
        if (filename.toLowerCase().endsWith(ext)) {
            return filename;
        } else {
            return filename + ext;
        }
    }

    // Static method to read all bytes from a file
    public static byte[] getFile(String filename) throws IOException {
        File file = new File(filename);
        return Files.readAllBytes(file.toPath());
    }
}
