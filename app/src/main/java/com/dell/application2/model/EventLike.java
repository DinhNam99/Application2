package com.dell.application2.model;

public class EventLike {

    private int countLike;
    private int pos;
    boolean activeLike;
    public EventLike(int countLike,int pos,boolean activeLike){
        this.countLike = countLike;
        this.pos = pos;
        this.activeLike = activeLike;
    }

    public int getCountLike() {
        return countLike;
    }

    public int getPos() {
        return pos;
    }

    public boolean isActiveLike() {
        return activeLike;
    }
    public static class EventLikeItemImage{
        private int countLike;
        private int pos;
        private int posItem;
        boolean activeLike;
        public EventLikeItemImage(int countLike,int pos,int posItem,boolean activeLike){
            this.countLike = countLike;
            this.pos = pos;
            this.activeLike = activeLike;
            this.posItem = posItem;
        }

        public int getCountLike() {
            return countLike;
        }

        public int getPos() {
            return pos;
        }

        public boolean isActiveLike() {
            return activeLike;
        }

        public int getPosItem() {
            return posItem;
        }
    }
}
