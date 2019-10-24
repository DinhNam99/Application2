package com.dell.application2.model;

public class EventComment {
    private int countComment;
    private int pos;
    public EventComment(int countComment,int pos){
        this.countComment = countComment;
        this.pos = pos;
    }

    public int getCountComment() {
        return countComment;
    }

    public int getPos() {
        return pos;
    }

    public static class EventCommentItemImage{
        private int countComment;
        private int pos;
        private int posItem;
        public EventCommentItemImage(int countComment,int pos, int posItem){
            this.countComment = countComment;
            this.pos = pos;
            this.posItem = posItem;
        }

        public int getCountComment() {
            return countComment;
        }

        public int getPos() {
            return pos;
        }

        public int getPosItem() {
            return posItem;
        }
    }
}
