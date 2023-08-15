package library.controller;

import custom.TableGenerate;
import library.models.Member;

import java.util.ArrayList;

public class MemberController {
    private final ArrayList<Member> members;

    // Konstruktor untuk inisialisasi ArrayList members
    public MemberController() {
        members = new ArrayList<>();
    }

    // Menghasilkan ID baru untuk member
    private String getNewId() {
        if (members.size() == 0) return "1";
        else return String.valueOf(Integer.parseInt(members.get(members.size()-1).getId()) + 1);
    }

    // Mendapatkan objek Member berdasarkan indeks
    public Member getMemberByIndex(int index) {
        if (index < 0) return null;
        return members.get(index);
    }

    // Memeriksa apakah member ada berdasarkan input ID atau nama
    public boolean isMemberExistByInput(String input) {
        for (Member member: members) {
            if (member.getId().equals(input) || member.getName().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    // Mendapatkan indeks member berdasarkan input ID atau nama
    public int getIndexMemberByInput(String input) {
        for (int indexMember = 0; indexMember < members.size(); indexMember++) {
            if (members.get(indexMember).getId().equals(input) || members.get(indexMember).getName().equalsIgnoreCase(input)) {
                return indexMember;
            }
        }
        return -1;
    }

    // Menambahkan member baru ke dalam ArrayList
    public void addMember(String memberName) {
        members.add(new Member(getNewId(), memberName));
    }

    // Menonaktifkan member berdasarkan input ID atau nama
    public boolean removeMember(String input) {
        if (isMemberExistByInput(input)) {
            members.get(getIndexMemberByInput(input)).deactivated();
            return true;
        }
        return false;
    }

    // Menampilkan semua member yang aktif
    public void showMembers() {
        showMembersByInput("");
    }

    // Menampilkan member yang sesuai dengan input ID atau nama
    public void showMembersByInput(String input) {
        boolean memberIsNotExist = true;

        // Inisialisasi objek TableGenerate untuk mencetak tabel
        TableGenerate tableGenerate = new TableGenerate("",
                new String[]{"ID", "Nama Member"},
                new char[]{'c', 'l'},
                new int[]{5, 50});
        tableGenerate.printSubTitle();

        // Iterasi melalui daftar member untuk mencetak tabel
        for (int indexMember = 0; indexMember < members.size(); indexMember++) {
            if (members.get(indexMember).getStatus().equalsIgnoreCase("aktif")) {
                if (members.get(indexMember).getId().equals(input) ||
                        members.get(indexMember).getName().toLowerCase().contains(input.toLowerCase())) {
                    tableGenerate.printBody(indexMember, new String[]{
                            members.get(indexMember).getId(),
                            members.get(indexMember).getName()
                    });
                    memberIsNotExist = false;
                }
            }
        }

        // Mencetak pesan jika member tidak tersedia
        if (memberIsNotExist) {
            tableGenerate.printMessage("Member tidak tersedia.");
        }
        tableGenerate.line();
    }
}
