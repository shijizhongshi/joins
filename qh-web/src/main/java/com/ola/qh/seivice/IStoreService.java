package com.ola.qh.seivice;

import com.ola.qh.util.FileStorageException;

public interface IStoreService {

	public String storeUrl(String fname, byte[] content)throws FileStorageException;
}
