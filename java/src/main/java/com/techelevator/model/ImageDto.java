package com.techelevator.model;

public class ImageDto {
    public Data getData() {
        return data;
    }

    private Data data;
    private boolean success;
    private int status;

    private static class Data {
        private String id;
        private String title;
        private String url_viewer;
        private String url;
        private String display_url;
        private int size;
        private int time;
        private int expiration;
        private Image image;
        private Thumb thumb;
        private Medium medium;
        private String delete_url;

        private static class Image {
            private String filename;
            private String name;
            private String mime;
            private String extension;
            private String url;

            public Image () {}

            public Image(String filename, String name, String mime, String extension, String url) {
                this.filename = filename;
                this.name = name;
                this.mime = mime;
                this.extension = extension;
                this.url = url;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMime() {
                return mime;
            }

            public void setMime(String mime) {
                this.mime = mime;
            }

            public String getExtension() {
                return extension;
            }

            public void setExtension(String extension) {
                this.extension = extension;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
        private static class Thumb {
            private String filename;
            private String name;
            private String mime;
            private String extension;
            private String url;

            public Thumb() {}

            public Thumb(String filename, String name, String mime, String extension, String url) {
                this.filename = filename;
                this.name = name;
                this.mime = mime;
                this.extension = extension;
                this.url = url;
            }

            public String getFilename() {
                return filename;
            }

            public String getName() {
                return name;
            }

            public String getMime() {
                return mime;
            }

            public String getExtension() {
                return extension;
            }

            public String getUrl() {
                return url;
            }
        }
        private static class Medium {
            private String filename;
            private String name;
            private String mime;
            private String extension;
            private String url;

            public Medium() {}

            public Medium(String filename, String name, String mime, String extension, String url) {
                this.filename = filename;
                this.name = name;
                this.mime = mime;
                this.extension = extension;
                this.url = url;
            }

            public String getFilename() {
                return filename;
            }

            public String getName() {
                return name;
            }

            public String getMime() {
                return mime;
            }

            public String getExtension() {
                return extension;
            }

            public String getUrl() {
                return url;
            }
        }

        public Data() {}

        public Data(String id, String title, String url_viewer, String url, String display_url, int size, int time, int expiration, Image image, Thumb thumb, Medium medium, String delete_url) {
            this.id = id;
            this.title = title;
            this.url_viewer = url_viewer;
            this.url = url;
            this.display_url = display_url;
            this.size = size;
            this.time = time;
            this.expiration = expiration;
            this.image = image;
            this.thumb = thumb;
            this.medium = medium;
            this.delete_url = delete_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl_viewer() {
            return url_viewer;
        }

        public void setUrl_viewer(String url_viewer) {
            this.url_viewer = url_viewer;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDisplay_url() {
            return display_url;
        }

        public void setDisplay_url(String display_url) {
            this.display_url = display_url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getExpiration() {
            return expiration;
        }

        public void setExpiration(int expiration) {
            this.expiration = expiration;
        }

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public Thumb getThumb() {
            return thumb;
        }

        public void setThumb(Thumb thumb) {
            this.thumb = thumb;
        }

        public Medium getMedium() {
            return medium;
        }

        public void setMedium(Medium medium) {
            this.medium = medium;
        }

        public String getDelete_url() {
            return delete_url;
        }

        public void setDelete_url(String delete_url) {
            this.delete_url = delete_url;
        }
    }

    public ImageDto() {}

    public ImageDto(Data data, boolean success, int status) {
        this.data = data;
        this.success = success;
        this.status = status;
    }


    public String getDataUrl() {
        return this.data.getUrl();
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }
}
