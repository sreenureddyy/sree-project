package com.sree.fileupload;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sree.web.BaseBean;

/**
 * @author YSReddi
 * 
 */
@Scope(value = "request")
@Component("fileUploadBean")
@SuppressWarnings( { "serial", "unused" })
public class FileUploadBean extends BaseBean {
	private static Logger log = Logger.getLogger(FileUploadBean.class);

	private ArrayList<FileTO> files = new ArrayList<FileTO>();
	private int uploadsAvailable = 5;
	private boolean autoUpload = true;
	private boolean useFlash = true;

	public int getSize() {
		if (getFiles().size() > 0) {
			return getFiles().size();
		} else {
			return 0;
		}
	}

	public FileUploadBean() {
	}

	public void paint(OutputStream stream, Object object) throws IOException {
		stream.write(files.get((Integer) object).getData());
	}

	public void listener(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		FileTO file = new FileTO();
		file.setLength(item.getFileSize());
		file.setName(item.getFileName());
		file.setData(item.getData());
		files.add(file);
		uploadsAvailable--;
	}

	public String clearUploadData() {
		files.clear();
		setUploadsAvailable(5);
		return null;
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}


	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	public ArrayList<FileTO> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<FileTO> files) {
		this.files = files;
	}

}
