class WorkerThread extends Thread {
    private final Resource resource;
    private final int id;

    public WorkerThread(Resource resource, int id) {
        this.resource = resource;
        this.id = id;
    }

    @Override
    public void run() {
        resource.useResource(id);
    }
}
