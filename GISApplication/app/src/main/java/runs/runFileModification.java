package runs;

import android.os.FileObserver;

import com.gis.gisapplication.MainActivity;

/**
 * This class implements {@link Runnable}.
 * The call method is use for the file modification.
 *
 * @author Orel and Samuel.
 */
public class runFileModification extends MainActivity implements Runnable {

    private String file;

    /**
     * Constructor.
     *
     * @param file
     * @throws InterruptedException
     */
    public runFileModification(String file) throws InterruptedException {
        this.file = file;
    }

    /**
     * This run method is used to detect any action into the folder/file imported by the user.
     * Use of the library of {@link android.webkit.WebChromeClient.FileChooserParams}.
     */
    @Override
    public void run() {
        FileObserver fileObserver = new FileObserver(file) {
            @Override
            public void onEvent(int event, String path) {
                switch (event) {
                    case ALL_EVENTS:
                        modificationFileAppears(path);
                        break;
                    case DELETE:
                        removeFile(path);
                        break;
                    case DELETE_SELF:
                        removeFile(path);
                        break;
                    case CREATE:
                        modificationFileAppears(path);
                        break;
                    case MODIFY:
                        modificationFileAppears(path);
                        break;
                    default:
                        break;
                }
            }
        };
        fileObserver.startWatching();
    }

}



