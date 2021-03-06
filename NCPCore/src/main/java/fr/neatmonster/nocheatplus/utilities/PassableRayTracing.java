package fr.neatmonster.nocheatplus.utilities;

public class PassableRayTracing extends RayTracing{

    protected BlockCache blockCache = null;

    protected boolean collides = false;

    protected boolean ignorefirst = false;

    public BlockCache getBlockCache() {
        return blockCache;
    }

    public void setBlockCache(BlockCache blockCache) {
        this.blockCache = blockCache;
    }

    /**
     * Set from PlayerLocation instances. Currently takes BlockCache from the from-location.
     * @param from
     * @param to
     */
    public void set(final PlayerLocation from, final PlayerLocation to){
        set(from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ());
        setBlockCache(from.getBlockCache()); // TODO: This might better be done extra.
    }

    /* (non-Javadoc)
     * @see fr.neatmonster.nocheatplus.utilities.RayTracing#set(double, double, double, double, double, double)
     */
    @Override
    public void set(double x0, double y0, double z0, double x1, double y1, double z1) {
        super.set(x0, y0, z0, x1, y1, z1);
        collides = false;
        ignorefirst = false;
    }

    public boolean collides(){
        return collides;
    }

    /**
     * Ignore the first block. Must be called after set, because set will override this with false.
     */
    public void setIgnorefirst(){
        this.ignorefirst = true;
    }

    /**
     * Test if the first block is set to be ignored (resets to false with set).
     * @return
     */
    public boolean getIgnoreFirst(){
        return ignorefirst;
    }

    /**
     * Remove reference to BlockCache.
     */
    public void cleanup(){
        if (blockCache != null){
            blockCache = null;
        }
    }

    @Override
    protected boolean step(final int blockX, final int blockY, final int blockZ, final double oX, final double oY, final double oZ, final double dT, final boolean isPrimary) {
        // Just delegate.
        if (step == 1 && ignorefirst){
            return true;
        }
        if (BlockProperties.isPassableRay(blockCache, blockX, blockY, blockZ, oX, oY, oZ, dX, dY, dZ, dT)){
            return true;
        }
        else{
            collides = true;
            return false;
        }
    }

}
