package server.clip.region;

//bitches

public class MemoryArchive
{

    public MemoryArchive(ByteStream cache, ByteStream index)
    {
        this.cache = cache;
        this.index = index;
    }

    public byte[] get(int dataIndex)
    {
        if(index.length() < dataIndex * 12)
            return null;
        try
        {
            index.setOffset(dataIndex * 12);
            long fileOffset = index.getLong();
            int fileSize = index.getInt();
            cache.setOffset(fileOffset);
            byte buffer[] = cache.read(fileSize);
            return buffer;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public int contentSize()
    {
        return index.length() / 12;
    }

    private ByteStream cache;
    private ByteStream index;
    private static final int INDEX_DATA_CHUNK_SIZE = 12;
}