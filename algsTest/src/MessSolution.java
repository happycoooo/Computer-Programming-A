
    /*
样例1：

输入
3 3
S.#
.#.
.#T

输出
-1

样例2：
输入
5 5
S.###
#....
#.#.#
#.#.#
#.#.T

输出
8

样例3：
输入
6 6
...S..
.#.##.
......
...##.
.#.#..
.T....

输出
7
*/
import java.util.*;

    public class MessSolution {

        //要用Queue的时候，如果是用<Integer>,那每个点只能存一个数字
//由于坐标至少要有x、y两个数字，所以必须建新类node,然后Queue<node>
        static class node{
            int x;
            int y;
            int step;	//step用于存放起点到当前坐标的最少步数（即层数）
            //在这里还可以存其他某些内容

            node(int x,int y,int step){
                this.x=x;
                this.y=y;
                this.step=step;
            }
        }

        static int MAXV=999;

        static int n,m;		 				       //大小为n*m的地图
        static int[][] map2=new int[MAXV][MAXV];   //二选一,输入全为数字的地图
        static char[][] map=new char[MAXV][MAXV];  //二选一,输入含有字符的地图
        static boolean[][] inq=new boolean[MAXV][MAXV];	 //判定该点是否进过队
        static int sp=-1;    //最终结果shortest path，用于存最短路径的长度。如果无路径，返回-1
        //move数组的元素顺序，会决定遍历时的顺序，本例中顺序为“上下左右”
        static int[][] move= {{-1,0},{1,0},{0,-1},{0,1}};

        //bfs算法
        public static void bfs(int x,int y) {
            //如果有需要，也可以把queue放到方法外面，加上static，作为类变量
            Queue<node> q=new LinkedList<node>();
            q.add(new node(x,y,0));
            inq[x][y]=true;

            while(!q.isEmpty()) {

                //1、进来之后，要做什么
                node temp=q.poll();
//			inq[temp.x][temp.y]=true;  //错误，inq数组的含义为结点是否已入过队，而非结点是否已被访问

                //2、如果是终点，要做什么
                if(map[temp.x][temp.y]=='T') {
                    sp=temp.step;
                    return ;
                }

                //3、如果不是终点，要做什么
                for(int i=0;i<4;i++) { //循环4次，得到4个相邻位置

                    //不能直接改变原结点中的x,y,path的值，要另设变量
                    int nx=temp.x+move[i][0];
                    int ny=temp.y+move[i][1];
                    int nstep=temp.step+1;

                    if(check(nx,ny)) {
                        q.offer(new node(nx,ny,nstep));
                        inq[nx][ny]=true;  //注意，结点入队时，要立即标记已入队
                    }
                }
            }
        }

        //边界条件和约束条件的判断,约束条件由实际题目决定
        public static boolean check(int x,int y) {
            //注意是map[x][y]!='#',如果改成map[x][y]=='.',会忽略‘T’的情况
            if(x>=0&&y>=0&&x<n&&y<m&&!inq[x][y]&&map[x][y]!='#')
                return true;
            else return false;
        }

        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);

            //不能在n,m之前写int
            n=sc.nextInt();
            m=sc.nextInt();

            int x=0,y=0;
            for(int i=0;i<n;i++) {
                String s=sc.next();
                for(int j=0;j<s.length();j++) {
                    if(s.charAt(j)=='S') {
                        x=i;
                        y=j;
                    }
                }
                map[i]=s.toCharArray();
            }

            bfs(x,y);

//		如果跑完dfs后还需要进行其他某些操作，就在这里写
            System.out.println(sp);
            sc.close();

        }
    }
