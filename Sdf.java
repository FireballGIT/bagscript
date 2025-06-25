package bagstudios.bagscript.mods.Sdf;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.util.HashMap;

public class Sdf {
    // Class-level globals map to hold variables by name
    private HashMap<String, Object> globals = new HashMap<>();

    // Clears console if addon is empty, else clears variable in globals map
    public void clear(String addon) {
        if (addon.equals("")) {
            try {
                // Clears Windows console
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // Clear variable by setting to null
            globals.put(addon, null);
        }
    }

    // Simple print method
    public void println(String str) {
        System.out.println(str);
    }

    /**
     * Frame method to perform actions on frames.
     * @param action The action: "import", "move", "create"
     * @param name The name of the file or frame
     * @param currentFrame The current frame index (passed in but can't be modified directly here)
     * @param movingTo The frame index to move to (same as above)
     * @param eql Possibly a flag or comparison int (not fully clear)
     */
    public void frame(String action, String name, int currentFrame, int movingTo, int eql) {
        if (action.equals("import") && !name.equals("")) {
            try {
                byte[] fileBytes = getFile(fixExtension(name, ".png"));
                // TODO: process fileBytes (e.g., load image data, create frame, etc)
                System.out.println("Imported file: " + name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (action.equals("move") && !name.equals("")) {
            try {
                // Example: just print out movingTo (can't modify int params here)
                System.out.println("Moving to frame: " + movingTo);
                // To actually move or update state, you'd want to store/mutate class fields or return values
            } catch (Exception e) {
                System.out.println("ERROR! " + movingTo + " is not a number.");
            }
        } else if (action.equals("create") && !name.equals("")) {
            // Example: create a new Frame object (you need a Frame class!)
            // Frame frame = new Frame(name, someFileOrData);
            System.out.println("Create action is not fully implemented yet.");
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
