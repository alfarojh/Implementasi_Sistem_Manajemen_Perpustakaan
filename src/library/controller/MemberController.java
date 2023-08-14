package library.controller;

import library.models.Member;

import java.util.ArrayList;

public class MemberController {
    private final ArrayList<Member> members;

    public MemberController() {
        members = new ArrayList<>();
    }

    private String getNewId() {
        if (members.size() == 0) return "1";
        else return String.valueOf(Integer.parseInt(members.get(members.size()-1).getId()) + 1);
    }

    public Member getMemberByIndex(int index) {
        if (index < 0) return null;
        return members.get(index);
    }

    public boolean isMemberExistByInput(String input) {
        for (Member member: members) {
            if (member.getId().equals(input)) {
                return true;
            } else if (member.getName().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public int getIndexMemberByInput(String input) {
        for (int indexMember = 0; indexMember < members.size(); indexMember++) {
            if (members.get(indexMember).getId().equals(input)) {
                return indexMember;
            } else if (members.get(indexMember).getName().equalsIgnoreCase(input)) {
                return indexMember;
            }
        }
        return -1;
    }

    public void addMember(String memberName) {
        members.add(new Member(getNewId(), memberName));
    }

    public boolean removeMember(String input) {
        if (isMemberExistByInput(input)) {
            members.get(getIndexMemberByInput(input)).deactivated();
            return true;
        }
        return false;
    }

    public void showMembers() {
        boolean memberIsNotExist = true;

        for (Member member: members) {
            if (member.getStatus().equalsIgnoreCase("active")) {
                System.out.println("ID Member: " + member.getId());
                System.out.println("Nama Member: " + member.getName());
                System.out.println();
                memberIsNotExist = false;
            }
        }
        if (memberIsNotExist) {
            System.out.println("Member tidak tersedia.");
        }
    }
}
